
package com.fastjrun.enhanced.mock;

import java.util.Date;
import com.fastjrun.demo.service.UserServiceTask;
import org.springframework.stereotype.Service;

@Service
public class UserServiceTaskMock
    implements UserServiceTask
{


    @Override
    public void unlockUserPwd(Date date) {
    }

    @Override
    public void inValidUserLoginCredential(Date date) {
    }

}
