package org.tianly.InvocationHandler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tianly.service.SaleAdiService;
import org.tianly.service.SaleNikeService;
import org.tianly.service.impl.SaleAdiServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CompanyReflectTest {
    @Autowired
    private SaleAdiService saleAdiService;

    @Autowired
    private SaleNikeService saleNikeService;

    @Test
    public void test() {
        InvocationHandler saleAdiServiceCompanyReflect = new CompanyReflect<>(saleAdiService);
        SaleAdiService saleAdiServiceDyn =
                (SaleAdiService) Proxy.newProxyInstance(SaleAdiService.class.getClassLoader(),
                        new Class<?>[]{SaleAdiService.class}, saleAdiServiceCompanyReflect);
        saleAdiServiceDyn.sale("运动鞋299");

    }

    public static void main(String[] args) {
        SaleAdiService saleAdiService = new SaleAdiServiceImpl();
        InvocationHandler saleAdiServiceCompanyReflect = new CompanyReflect<>(saleAdiService);
        SaleAdiService saleAdiServiceDyn =
                (SaleAdiService) Proxy.newProxyInstance(SaleAdiService.class.getClassLoader(),
                        new Class<?>[]{SaleAdiService.class}, saleAdiServiceCompanyReflect);
        saleAdiServiceDyn.sale("运动鞋299");
    }
}