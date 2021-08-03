package cn.dszx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class thTest {
    public static void main(String[] args) throws JsonProcessingException {
        String dszxxz = new ObjectMapper().writeValueAsString("dszxxz");
        System.out.println(dszxxz);
    }
}
