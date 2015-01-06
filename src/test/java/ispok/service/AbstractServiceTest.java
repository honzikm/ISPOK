/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import javax.transaction.Transactional;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml", "/test-applicationContext-security.xml"})
//@ContextConfiguration(locations = {"/WEB-INF/context/applicationContext.xml"})
@Profile(value = "")
@TransactionConfiguration(defaultRollback = true, transactionManager = "txManager")
@Transactional
public abstract class AbstractServiceTest {

    public AbstractServiceTest() {
    }

}
