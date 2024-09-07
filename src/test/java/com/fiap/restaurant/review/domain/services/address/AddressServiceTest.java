package com.fiap.restaurant.review.domain.services.address;

import com.fiap.restaurant.review.infra.models.AddressModel;
import com.fiap.restaurant.review.infra.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private SaveAddressService saveAddressService;

    private AddressModel addressModel;

    @BeforeEach
    void setUp() {
        addressModel = new AddressModel();
        addressModel.setPublicPlace("123 Main St");
        addressModel.setCity("Sample City");
        addressModel.setCep("12345");
    }

    @Test
    void testSaveAddress() {
        // Arrange
        when(addressRepository.save(any(AddressModel.class))).thenReturn(addressModel);

        // Act
        AddressModel savedAddress = saveAddressService.save(addressModel);

        // Assert
        assertNotNull(savedAddress);
        assertEquals("123 Main St", savedAddress.getPublicPlace());
        assertEquals("Sample City", savedAddress.getCity());
        assertEquals("12345", savedAddress.getCep());
        verify(addressRepository, times(1)).save(addressModel);
    }
}
