package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.elca.training.service.GroupService;
import vn.elca.training.service.ProjectService;

/**
 * @author vlp
 */
@RestController
public class MainController extends AbstractApplicationController {

    @Autowired
    @Qualifier("projectServiceImpl")
    private ProjectService projectService;
    @Autowired
    private GroupService groupService;

    @Value("${application.title}")
    private String title;

    @Value("${application.message}")
    private String message;

    @GetMapping("/main")
    public String main() {
        groupService.initGroup();
        return title + ". " + String.format(message, projectService.count());
    }
}
