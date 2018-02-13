package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrdersFilterTest {
    @Test
    public void tryGetOrders() throws IOException, ServletException {
        OrdersFilter servlet = new OrdersFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getParameter("isEmpty")).thenReturn("-1");
        when(request.getParameter("model")).thenReturn("%");
        when(request.getParameter("orderDate")).thenReturn("0");
        when(response.getWriter()).thenReturn(writer);
        servlet.doGet(request, response);

        writer.flush();
        System.out.println(stringWriter.toString());
    }

}