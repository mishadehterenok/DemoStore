package com.example.store.control;

import com.example.store.dao.ReceiptDao;
import com.example.store.entity.Receipt;
import com.example.store.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/receipts", name = "receipts")
public class UserReceiptsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Receipt> receipts = ReceiptDao.getInstance().findReceiptsByUserId(user.getId());
        int total = 0;
        for (Receipt receipt : receipts){
            int count = receipt.getCount();
            int price = receipt.getProduct().getPrice();
            int sum = count * price;
            total+=sum;
        }
        req.setAttribute("receipts", receipts);
        req.setAttribute("total", total);
        getServletContext().getRequestDispatcher("/res/store/user-receipts.jsp").forward(req,resp);
    }
}
