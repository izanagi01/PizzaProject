package com.devcamp.pizza365.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcamp.pizza365.EntityRespository.iOfficeRespository;
import com.devcamp.pizza365.entity.Office;

@Service
public class OfficeService {
    
    @Autowired
    iOfficeRespository iOffice ;

        // tìm  all office
        public ResponseEntity <List<Office>> getOfficesList() {
            try {
                List<Office> office = new ArrayList<Office>();
                iOffice.findAll().forEach(office::add);
                return new ResponseEntity<>(office, HttpStatus.OK);
        }
            catch (Exception e){
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
         }
    
    
        // tìm bằng id office 
        public ResponseEntity<Object> getOfficesById(Integer id) {
            
                Optional<Office> office = iOffice.findById(id);
                
            if(office.isPresent()){
                return new ResponseEntity<>(office.get(), HttpStatus.OK);
            } 
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    
        
        }
    
        
        // tạo mới office cách đơn giản
        public ResponseEntity<Object> createOffice(Office office) {
            try {
                Optional<Office> newOffice = iOffice.findById(office.getId());
                
               if(newOffice.isPresent()){
                return ResponseEntity.unprocessableEntity().body(" Office already exsit  ");
               }
               else {
                    return new ResponseEntity<>(iOffice.save(office) , HttpStatus.CREATED) ;
               }
            } catch (Exception e) {
                System.out.println("+++++++++++++++++++++::::: " + e.getCause().getCause().getMessage());
                return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Album: " + e.getCause().getCause().getMessage());
    
            }
        }
    
    
        // update office 
        public ResponseEntity<Object> updateOffice(int id , Office office) {
          
            try {
                Optional<Office> newOffice = iOffice.findById(id);
                if(newOffice.isPresent()){
                    Office updateOffice = newOffice.get() ;
                        updateOffice.setCity(office.getCity());
                        updateOffice.setPhone(office.getPhone());
                        updateOffice.setAddressLine(office.getAddressLine());
                        updateOffice.setState(office.getState());
                        updateOffice.setCountry(office.getCountry());
                        updateOffice.setTerritory(office.getTerritory());
            
                        return new ResponseEntity<>(iOffice.save(updateOffice) , HttpStatus.OK) ;
                   }
                   else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                   }
                } 
            catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    
            }         
        }
    
        // delete office
        public ResponseEntity<Object> deleteOffice (int id ){
            try {
                iOffice.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
