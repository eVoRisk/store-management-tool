package com.store.management.tool.service;

import com.store.management.tool.exception.NotFoundException;
import com.store.management.tool.repository.CategoryRepository;
import com.store.management.tool.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static com.store.management.tool.helper.DataProvider.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private ProductServiceImpl victim;

    @Test
    void shouldSuccessfulAddProduct() {
        var request = defaultProductRequest();

        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(getCategory()));
        when(productRepository.save(any())).thenReturn(getProduct());

        var actual = victim.add(request);

        verify(productRepository, times(1)).save(any());
        verify(categoryRepository, times(1)).findByName(anyString());
        verify(modelMapper, times(2)).map(any(), any());

        assertEquals(request.getName(), actual.getName());
        assertEquals(request.getDescription(), actual.getDescription());
        assertEquals(request.getPrice(), actual.getPrice());
        assertEquals(request.getStock(), actual.getStock());
        assertEquals(request.getCategory().getName(), actual.getCategory().getName());
    }

    @Test
    void shouldThrowNotFoundException() {
        when(categoryRepository.findByName(anyString())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> victim.add(defaultProductRequest()));
    }
}
