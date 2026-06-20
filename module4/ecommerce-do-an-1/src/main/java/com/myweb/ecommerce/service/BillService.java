package com.myweb.ecommerce.service;

import com.myweb.ecommerce.dto.request.PlaceOrderRequest;
import com.myweb.ecommerce.model.Bill;
import com.myweb.ecommerce.model.BillProduct;
import com.myweb.ecommerce.repository.BillProductRepository;
import com.myweb.ecommerce.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillProductRepository billProductRepository;

    public void save(PlaceOrderRequest req) {
        // Save bill
        Bill bill = new Bill();
        bill.setBillId("20260620035520");
        bill.setFirstname(req.getFirstname());
        bill.setLastname(req.getLastname());
        bill.setEmail(req.getEmail());
        bill.setPhone(req.getPhone());
        bill.setAddress(req.getAddress());
        Bill result = billRepository.save(bill);

        // Save bill_product
        BillProduct billProduct1 = new BillProduct();
        billProduct1.setBillId(result.getBillId());
        billProduct1.setNameProduct("Iphone 15");
        billProduct1.setPrice(9000.0);
        billProduct1.setQty(1);
        billProduct1.setTotal(9000);
        billProductRepository.save(billProduct1);

        BillProduct billProduct2 = new BillProduct();
        billProduct2.setBillId(result.getBillId());
        billProduct2.setNameProduct("Samsung S24");
        billProduct2.setPrice(6000.0);
        billProduct2.setQty(1);
        billProduct2.setTotal(6000);
        billProductRepository.save(billProduct2);
    }
}
