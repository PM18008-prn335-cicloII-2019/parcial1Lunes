/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine.boundary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.ues.occ.ingenieria.prn335_2019.cine.DataAccessException;
import sv.ues.occ.ingenieria.prn335_2019.cine.control.MenuConsumibleBean;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.MenuConsumible;

/**
 *
 * @author jcpenya
 */
@ExtendWith(MockitoExtension.class)
public class MenuConsumibleServletTest {
    
    HttpServletRequest mockRQ;
    HttpServletResponse mockRP;
    MenuConsumibleServlet cut;

    public MenuConsumibleServletTest() {
        
        mockRQ = Mockito.mock(HttpServletRequest.class);
        mockRP = Mockito.mock(HttpServletResponse.class);
        
        cut = Mockito.spy(new MenuConsumibleServlet());
        
    }
    
    public String getCarnet(){
       return "PM18008"; 
    }
    
    @Test
    public void testProcessRequest() throws IOException{

        doNothing().when(mockRP).setContentType(Mockito.any(String.class));
        
        Map<String,String[]> mockMSS = Mockito.mock(Map.class);
        Mockito.when(mockRQ.getParameterMap()).thenReturn(mockMSS);
        Mockito.when(mockMSS.containsKey(Mockito.any(String.class))).thenReturn(true);
        
        PrintWriter mockPW = Mockito.mock(PrintWriter.class);
        Mockito.when(mockRP.getWriter()).thenReturn(mockPW);
        
        MenuConsumibleBean mockMCB = Mockito.mock(MenuConsumibleBean.class);
        cut.menuConsumibleBean = mockMCB;
        
        
        Mockito.when(mockRQ.getParameter(Mockito.any(String.class))).thenReturn("");
        try {
            Mockito.when(mockMCB.countByNombreLike(Mockito.any(String.class))).thenReturn(2);
        } catch (DataAccessException ex) {

        }
        
        List<MenuConsumible> mockLMC = Mockito.mock(ArrayList.class);Mockito.when(mockLMC.size()).thenReturn(2);
        try {
            Mockito.when(mockMCB.findByNombreLike(Mockito.any(String.class),Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(mockLMC);
        } catch (DataAccessException ex) {

        }
        
        Mockito.when(mockLMC.size()).thenReturn(2);
        
        MenuConsumible mockMC = Mockito.mock(MenuConsumible.class);
        Mockito.when(mockLMC.get(Mockito.any(Integer.class))).thenReturn(mockMC);
        
        Mockito.when(mockMC.getIdConsumible()).thenReturn(0);
        Mockito.when(mockMC.getNombre()).thenReturn("");
        Mockito.when(mockMC.getPrecio()).thenReturn(0.0);
        
        
        try{
            
            cut.processRequest(mockRQ, mockRP);
        
        }catch(Exception e){
        
        
        }
        
        try {
            Mockito.when(mockMCB.countByNombreLike(Mockito.any(String.class))).thenReturn(1);
        } catch (DataAccessException ex) {
            
        }
        
        try{
            
            cut.processRequest(mockRQ, mockRP);
        
        }catch(Exception e){
        
        
        }
        
   
       
        try {
            Mockito.when(mockMCB.countByNombreLike(Mockito.any(String.class))).thenReturn(0);
        } catch (DataAccessException ex) {
            
        }
        
        try{
            
            cut.processRequest(mockRQ, mockRP);
        
        }catch(Exception e){
        
        
        }
        
        Mockito.when(mockMSS.containsKey(Mockito.any(String.class))).thenReturn(false);
        
        try{
            
            cut.processRequest(mockRQ, mockRP);
        
        }catch(Exception e){
        
        
        }
        
        Mockito.when(mockRP.getWriter()).thenThrow(IOException.class);
        
        try{
            
            cut.processRequest(mockRQ, mockRP);
        
        }catch(Exception e){
        
        
        }
        
    }
    
    @Test
    public void testDoGet(){
        
        HttpServletRequest mockRQ = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockRP = Mockito.mock(HttpServletResponse.class);
        
        MenuConsumibleServlet spyCut = Mockito.spy(new MenuConsumibleServlet());
        
        try {
            doNothing().when(spyCut).processRequest(Mockito.any(HttpServletRequest.class),Mockito.any(HttpServletResponse.class));
        } catch (Exception e) {
            
        }
     
        try {
            spyCut.doGet(mockRQ, mockRP);
        } catch (Exception e) {
            
        } 
        
    }
    
    @Test
    public void testDoPost(){
        
        HttpServletRequest mockRQ = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockRP = Mockito.mock(HttpServletResponse.class);
        
        MenuConsumibleServlet spyCut = Mockito.spy(new MenuConsumibleServlet());
        
        try {
            doNothing().when(spyCut).processRequest(Mockito.any(HttpServletRequest.class),Mockito.any(HttpServletResponse.class));
        } catch (Exception e) {
            
        }
     
        try {
            spyCut.doPost(mockRQ, mockRP);
        } catch (Exception e) {
            
        } 
        
    }
    
    @Test
    public void testGetServletInfo(){
        
        cut.getServletInfo();
        
    }
    


   
}
