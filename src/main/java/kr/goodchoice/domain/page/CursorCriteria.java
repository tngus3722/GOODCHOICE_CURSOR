package kr.goodchoice.domain.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class CursorCriteria extends BaseCriteria {
    private String cursor = null;

    public <T extends CustomPageable> String getNextCursor(String endPoint, List<T> responseList) {
        return this
                .getNextUrlBase(endPoint)
                .append("&cursor=")
                .append(responseList.get(responseList.size() - 2).getCursor(this))
                .toString();
    }
}
