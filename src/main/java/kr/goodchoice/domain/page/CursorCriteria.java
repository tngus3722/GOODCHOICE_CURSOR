package kr.goodchoice.domain.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CursorCriteria extends BaseCriteria {
    private String cursor = "init";
}
