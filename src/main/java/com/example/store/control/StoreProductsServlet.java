package com.example.store.control;

import com.example.store.dao.ProductDao;
import com.example.store.dao.ReceiptDao;
import com.example.store.entity.Product;
import com.example.store.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/store-products", name = "products")
public class StoreProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> allProducts = ProductDao.getInstance().findAllProducts();
        req.setAttribute("products",allProducts);
        getServletContext().getRequestDispatcher("/res/store/store-products.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Map<String, String[]> parameterMap = req.getParameterMap();
       for ( Map.Entry<String, String[]> entry : parameterMap.entrySet()){
           int product_id = Integer.parseInt(entry.getKey());
           int count = Integer.parseInt(entry.getValue()[0]);
           if (count != 0) {
               Integer existProduct_id = ReceiptDao.getInstance().ifReceiptExistsReturnId(user.getId(), product_id);
               if (existProduct_id != null) {
                   ReceiptDao.getInstance().updateReceiptOnProductCount(existProduct_id, count);
               } else {
                   ReceiptDao.getInstance().saveReceipt(user.getId(), product_id, count);
               }
           }
       }
       resp.sendRedirect("/receipts");
    }
}
