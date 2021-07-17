package org.tianly.service.impl;

import org.springframework.stereotype.Service;
import org.tianly.service.SaleNikeService;

@Service
public class SaleNikeServiceImpl implements SaleNikeService {
    @Override
    public void sale(String name) {
        System.out.println("SaleNikeServiceImpl sale="+name);
    }
}
