package com.fiap.restaurant.review.domain.services.restaurant;

import com.fiap.restaurant.review.domain.exceptions.NotFoundException;
import com.fiap.restaurant.review.domain.services.address.SaveAddressService;
import com.fiap.restaurant.review.domain.services.resturant.RestaurantService;
import com.fiap.restaurant.review.infra.models.AddressModel;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

	@Mock
	private RestaurantRepository restaurantRepository;

	@Mock
	private SaveAddressService addressService;

	@InjectMocks
	private RestaurantService restaurantService;

	private RestaurantModel restaurantModel;

	@BeforeEach
	void setUp() {
		restaurantModel = new RestaurantModel();
		restaurantModel.setAddress(new AddressModel());
		restaurantModel.setAlwaysOpen(true); // ou false, dependendo do cenário que deseja testar
	}

	@Test
	void testSaveRestaurantAlwaysOpen() {
		// Mockando o comportamento do addressService e restaurantRepository
		when(addressService.save(any(AddressModel.class))).thenReturn(restaurantModel.getAddress());
		when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurantModel);

		// Executando o método a ser testado
		RestaurantModel savedRestaurant = restaurantService.save(restaurantModel);

		// Verificando as alterações feitas no método
		assertEquals(LocalTime.of(0, 0, 0), savedRestaurant.getOpenHour());
		assertEquals(LocalTime.of(23, 59, 59), savedRestaurant.getCloseHour());
		assertEquals(0, savedRestaurant.getTotalGrade());
	}

	@Test
	void testSaveRestaurantNotAlwaysOpen() {
		// Cenário onde o restaurante não está sempre aberto
		restaurantModel.setAlwaysOpen(false);

		// Mockando o comportamento do addressService e restaurantRepository
		when(addressService.save(any(AddressModel.class))).thenReturn(restaurantModel.getAddress());
		when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurantModel);

		// Executando o método a ser testado
		RestaurantModel savedRestaurant = restaurantService.save(restaurantModel);

		// Verificando que as horas de abertura e fechamento não foram alteradas
		assertNull(savedRestaurant.getOpenHour());
		assertNull(savedRestaurant.getCloseHour());
		assertEquals(0, savedRestaurant.getTotalGrade());
	}

	@Test
	void testFindByCnpjSuccess() {
		String cnpj = "12345678000100";
		RestaurantModel restaurant = new RestaurantModel();
		restaurant.setCnpj(cnpj);

		when(restaurantRepository.findResturantByCnpj(cnpj)).thenReturn(Optional.of(restaurant));

		RestaurantModel result = restaurantService.findByCnpj(cnpj);

		assertNotNull(result);
		assertEquals(cnpj, result.getCnpj());
		verify(restaurantRepository, times(1)).findResturantByCnpj(cnpj);
	}

	@Test
	void testFindByCnpjNotFound() {
		// Arrange
		String cnpj = "12345678000100";

		when(restaurantRepository.findResturantByCnpj(cnpj)).thenReturn(Optional.empty());

		// Act & Assert
		NotFoundException exception = assertThrows(NotFoundException.class, () -> restaurantService.findByCnpj(cnpj));
		assertEquals("m=findByCnpj Not Found Resturante with CNPJ = " + cnpj, exception.getMessage());
		verify(restaurantRepository, times(1)).findResturantByCnpj(cnpj);
	}
}
