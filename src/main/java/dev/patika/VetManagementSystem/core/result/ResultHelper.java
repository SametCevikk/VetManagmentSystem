package dev.patika.VetManagementSystem.core.result;

import dev.patika.VetManagementSystem.dto.response.CursorResponse;
import org.springframework.data.domain.Page;

public class ResultHelper {


    public static <T>ResultData<T> success(T data){
        return new ResultData<>(true,"Transaction Successful","200",data);
    }
    public static  Result success(){
        return new Result(true,"Transaction Successful","200");
    }

    public static <T> ResultData<CursorResponse<T>> cursor(T data, Page<T> pageData){
        CursorResponse<T> cursor= new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());
        return ResultHelper.success(cursor);
    }
}
