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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.List;
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
		restaurantModel.setAlwaysOpen(true);
	}

	@Test
	void testSaveRestaurantAlwaysOpen() {
		when(addressService.save(any(AddressModel.class))).thenReturn(restaurantModel.getAddress());
		when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurantModel);

		RestaurantModel savedRestaurant = restaurantService.save(restaurantModel);

		assertEquals(LocalTime.of(0, 0, 0), savedRestaurant.getOpenHour());
		assertEquals(LocalTime.of(23, 59, 59), savedRestaurant.getCloseHour());
		assertEquals(0, savedRestaurant.getTotalGrade());
	}

	@Test
	void testSaveRestaurantNotAlwaysOpen() {
		restaurantModel.setAlwaysOpen(false);

		when(addressService.save(any(AddressModel.class))).thenReturn(restaurantModel.getAddress());
		when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurantModel);

		RestaurantModel savedRestaurant = restaurantService.save(restaurantModel);

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
		String cnpj = "12345678000100";

		when(restaurantRepository.findResturantByCnpj(cnpj)).thenReturn(Optional.empty());

		NotFoundException exception = assertThrows(NotFoundException.class, () -> restaurantService.findByCnpj(cnpj));
		assertEquals("m=findByCnpj Not Found Resturante with CNPJ = " + cnpj, exception.getMessage());
		verify(restaurantRepository, times(1)).findResturantByCnpj(cnpj);
	}

	@Test
	void testFindAllAndPageableByFilter() {
		Pageable pageable = PageRequest.of(0, 10);
		String filter = "Restaurant";
		List<RestaurantModel> restaurantList = List.of(restaurantModel);
		Page<RestaurantModel> restaurantPage = new PageImpl<>(restaurantList);

		when(restaurantRepository.findAllResturantsByName(any(Pageable.class), anyString()))
				.thenReturn(restaurantPage);

		Page<RestaurantModel> result = restaurantService.findAllAndPageableByFilter(pageable, filter);

		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
		assertEquals(restaurantModel, result.getContent().get(0));
		verify(restaurantRepository, times(1)).findAllResturantsByName(pageable, filter);
	}
}
