package by.lostFinder.controllers;

import by.lostFinder.entities.AccountDetail;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/accountDetail")
public class AccountDetailController extends GenericController<AccountDetail, SimpleService<AccountDetail>> {
    @Autowired
    protected AccountDetailController(@Qualifier("accountDetailService") SimpleService<AccountDetail> service){
        super(service);
    }
}
