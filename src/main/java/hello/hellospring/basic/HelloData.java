package hello.hellospring.basic;

import lombok.Data;

@Data
// @Data는 @Getter , @Setter , @ToString , @EqualsAndHashCode , @RequiredArgsConstructor 를
// 자동으로 적용해준다.
public class HelloData {
    private String username;
    private int age;
}
