package com.frederikzwartbol.springboottwitterrebuild;

import com.frederikzwartbol.springboottwitterrebuild.features.authentication.models.Role;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootTwitterRebuild
        implements CommandLineRunner
{
    private final RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTwitterRebuild.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role(null,"USER"));
    }
}
