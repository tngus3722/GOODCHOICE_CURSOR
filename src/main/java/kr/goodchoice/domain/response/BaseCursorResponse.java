package kr.goodchoice.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.goodchoice.domain.page.CursorCriteria;
import kr.goodchoice.domain.page.CustomPageable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseCursorResponse<T extends CustomPageable> {
    private List<T> response;
    private String next;
    //private String previous;

    public BaseCursorResponse(List<T> response, CursorCriteria cursorCriteria, String endPoint) {
        if (response.size() == cursorCriteria.getLimit() + 1) {
            this.response = new ArrayList<>(response.subList(0, cursorCriteria.getLimit()));
            this.next = cursorCriteria
                    .getNextUrlBase(endPoint)
                    .append("&cursor=")
                    .append(response.get(response.size() - 2).getCursor(cursorCriteria))
                    .toString();
        } else
            this.response = response;
    }
}
