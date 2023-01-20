package shop.mtcoding.buyer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.buyer.model.Product;
import shop.mtcoding.buyer.model.ProductRepository;
import shop.mtcoding.buyer.model.PurchaseRepository;

/*
 * new 해서 ioc에 등록
 * @Controller, @Restcontroller, @Mapper, @Service, @Component
 */

@Service // IoC 컨테이너 등록
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public int 구매하기(int principalId, int productId, int count) {
        // 1. 상품 존재 확인(검증)
        Product product = productRepository.findById(productId);
        if (product == null) {
            return -1;
        }

        // 2. 수량 체크
        if (product.getQty() < count) {

            return -1;
        }

        // 3. 재고 수량 변경
        int result2 = productRepository.updateById(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQty() - count);

        if (result2 != 1) {
            return -1;
        }

        // 4. 구매 이력 남기기
        int result = purchaseRepository.insert(principalId, productId, count);
        if (result != 1) {
            return -1;
        }

        return 1;
    } // commit

}
