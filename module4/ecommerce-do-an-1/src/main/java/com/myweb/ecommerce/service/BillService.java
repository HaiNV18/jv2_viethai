package com.myweb.ecommerce.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myweb.ecommerce.dto.CartItemDto;
import com.myweb.ecommerce.dto.request.PlaceOrderRequest;
import com.myweb.ecommerce.model.Bill;
import com.myweb.ecommerce.model.BillProduct;
import com.myweb.ecommerce.repository.BillProductRepository;
import com.myweb.ecommerce.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillProductRepository billProductRepository;

    private final ObjectMapper objectMapper;

    public void save(PlaceOrderRequest req) {
        try {
            List<CartItemDto> cartItems = objectMapper.readValue(
                    req.getCartData(),
                    new TypeReference<List<CartItemDto>>() {}
            );
            String billId = "20260623035521";

            // Save Bill
            Bill bill = new Bill();
            bill.setBillId(billId);
            bill.setFirstname(req.getFirstname());
            bill.setLastname(req.getLastname());
            bill.setEmail(req.getEmail());
            bill.setPhone(req.getPhone());
            bill.setAddress(req.getAddress());

            Bill result = billRepository.save(bill);

            List<BillProduct> billProducts = cartItems.stream()
                    .map(item -> {
                        Integer total = (int) (item.getPrice() * item.getQty());

                        BillProduct billProduct = new BillProduct();
                        billProduct.setBillId(billId);
                        billProduct.setNameProduct(item.getName());
                        billProduct.setPrice(item.getPrice());
                        billProduct.setQty(item.getQty());
                        billProduct.setTotal(total);
                        billProductRepository.save(billProduct);
                        return billProduct;
            }).toList();

        } catch (Exception e) {
            throw new RuntimeException("Cannot save order", e);
        }
    }
}
