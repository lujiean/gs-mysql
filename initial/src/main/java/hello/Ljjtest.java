package hello;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ljjtest{

    @Id
    private Integer int_val;

    private String char_val;

    public void setInt_val(Integer int_val) {
		this.int_val = int_val;
    }
    
    public Integer getInt_val(){
        return int_val;
    }

    public void setChar_val(String char_val) {
		this.char_val = char_val;
    }
    
    public String getChar_val(){
        return char_val;
    }

}