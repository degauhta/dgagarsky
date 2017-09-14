package ru.dega.servlets;


import com.google.gson.Gson;
import ru.dega.dao.ItemDao;
import ru.dega.dao.sql.ItemSQLDao;
import ru.dega.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * ItemsJSON class.
 *
 * @author Denis
 * @since 11.09.2017
 */
public class ItemsJSON extends HttpServlet {
    /**
     * Handle a POST request.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException error
     * @throws IOException      error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemDao itemDao = new ItemSQLDao();
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getWriter());
        List<Item> list = new ArrayList<>();
        String show = req.getParameter("showNotDone");
        if (show.equals("false")) {
            list = itemDao.getAllItems();
        } else {
            list = itemDao.getNotDoneItems();
        }
        list.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getId() - o2.getId();
            }
        });
        writer.append(new Gson().toJson(list));
        writer.flush();
    }

    /**
     * Allow a servlet to handle a POST request.
     * The HTTP POST method allows the client to send data
     * of unlimited length to the Web server a single time
     * and is useful when posting information such as credit card numbers.
     *
     * @param req  request
     * @param resp response
     * @throws ServletException error
     * @throws IOException      error
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getWriter());
        String operationName = req.getParameter("operationName");
        boolean result = false;
        Item item;
        switch (operationName) {
            case "create":
                item = new Item(-1,
                        req.getParameter("description"),
                        new Timestamp(System.currentTimeMillis()),
                        false);
                result = create(item);
                break;
            case "update":
                item = new Item(Integer.valueOf(req.getParameter("trItemId")),
                        null, null,
                        Boolean.parseBoolean(req.getParameter("trItemDone")));
                result = update(item);
                break;
            case "delete":
                item = new Item(Integer.valueOf(req.getParameter("trItemId")),
                        null, null, false);
                result = delete(item);
                break;
            default:
                break;
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        writer.append(String.valueOf(result));
        writer.flush();
    }

    /**
     * Create to-do item.
     *
     * @param item new item
     * @return true if create successfully
     */
    private boolean create(Item item) {
        ItemDao itemDao = new ItemSQLDao();
        return itemDao.createItem(item);
    }


    /**
     * Update status of to-do item.
     *
     * @param item item
     * @return true if update successfully
     */
    private boolean update(Item item) {
        ItemDao itemDao = new ItemSQLDao();
        return itemDao.updateItem(item);
    }

    /**
     * Update status of to-do item.
     *
     * @param item item
     * @return true if update successfully
     */
    private boolean delete(Item item) {
        ItemDao itemDao = new ItemSQLDao();
        return itemDao.deleteItem(item);
    }
}