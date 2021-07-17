package org.tianly.service.impl;

import org.springframework.stereotype.Service;
import org.tianly.service.SaleAdiService;

@Service
public class SaleAdiServiceImpl implements SaleAdiService {
    @Override
    public void sale(String name) {
        System.out.println("SaleAdiServiceImpl sale="+name);
    }
}
