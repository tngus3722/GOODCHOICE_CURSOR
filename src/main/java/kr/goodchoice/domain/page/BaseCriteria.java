package kr.goodchoice.domain.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BaseCriteria {
    protected int limit = 10;
    protected String sortBy = "id";
    protected String order = "desc";

    public StringBuilder getNextUrlBase(String baseUrl) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(baseUrl)
                .append("?limit=").append(this.getLimit())
                .append("&order=").append(this.getOrder())
                .append("&sortBy=").append(this.getSortBy());
        return stringBuilder;
    }
}
