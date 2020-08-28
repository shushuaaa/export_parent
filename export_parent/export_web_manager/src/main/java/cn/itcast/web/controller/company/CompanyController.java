package cn.itcast.web.controller.company;

import cn.itcast.domain.company.Company;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import cn.itcast.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/list")
   public String list(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize,HttpServletRequest request ){
        PageInfo<Company> pageInfo=companyService.findByPage(pageNum,pageSize);
        request.setAttribute("pageInfo",pageInfo);
        return "company/company-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "company/company-add";
    }

    @RequestMapping("/edit")
    public String edit(Company company) {
        if (StringUtils.isEmpty(company.getId())) {
            companyService.save(company);
        } else {
            companyService.update(company);
        }
        return "redirect:/company/list.do";
    }

    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(String id){
        ModelAndView mv=new ModelAndView();
        Company company=companyService.findById(id);
        mv.addObject("company",company);
        mv.setViewName("company/company-update");
        return  mv;
    }

    @RequestMapping("/delete")
    public String delete(){
        return "redirect:/company/list.do";
    }

}
