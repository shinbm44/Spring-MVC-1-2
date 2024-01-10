package hello.hellospring.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // 서블릿 방식으로 작성한 http 쿼리 파라미터 전달 방식
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    // @ResponseBody는 @Controller를 @RestController로 바꾸는 대신 http에 직접 메세지를 전달하는 기능을 한다.
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    // v3에 비해서 @RequestParam이 빠졌는데 이는 단순타입일 경우 생략해도 @RequestParam이 있는 것으러 인식한다.

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }
    // 그렇지만 @RequestParam까지 빼는 것은 과하지 않나라는 고려


    // required가 true로 설정되었다면 url에서 해당 빠지면 오류가 난다. 기본값은 true
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){
        // 여기서 username이 빠지면, 400번대 오류가 발생하지만, age가 빠지면 500번대 오류가 발생
        // 400번은 없는 페이지를 요청하는 클라이언트의 요청 메세지 내용이 잫못된 경우
        // 500번은 서버에서 메세지 처리에 문제가 발생하는 것이다. --> 만약 age가 빠지면 null값이 들어갈텐데
        // int형은 null값이 들어갈 수 없다. ---> Integer로 바꾸면 해결

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(

            // 디폴트 값이 세팅 -> required가 필요없다.
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") Integer age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            // 디폴트 값이 세팅 -> required가 필요없다.
            @RequestParam Map<String, Object> paramMap,
            @RequestParam(defaultValue = "-1") Integer age){

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
    /*
     @RequestParam MultiValueMap
     MultiValueMap(key=[value1, value2, ...] ex) (key=userIds, value=[id1,
     id2])
     파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용하자.
     */


}
