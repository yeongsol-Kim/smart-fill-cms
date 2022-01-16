package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.FillLogService;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JdbcTemplateMemberRepository(dataSource);
    }

    @Bean
    public FillLogService fillLogService() {
        return new FillLogService(fillLogRepository());
    }

    public FillLogRepository fillLogRepository() {
        return new MemoryFillLogRepository();
    }
}
