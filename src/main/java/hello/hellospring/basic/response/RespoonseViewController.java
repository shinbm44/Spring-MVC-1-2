package hello.hellospring.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RespoonseViewController {


    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    //@ResponseBody 만약 이걸 위해 붙이면 html 페이지에 response/hello가 나온다.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello");
        return "response/hello";
    }
    // void를 반환하며 http 메세지 바디를 처리하는 파라미터가 없으면 요청 url 자체를 참고하여 논리 뷰 이름으로 사용한다.
    // 권장하지 않는다.
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello");
    }



}
