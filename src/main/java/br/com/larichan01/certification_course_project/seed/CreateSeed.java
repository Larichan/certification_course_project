package br.com.larichan01.certification_course_project.seed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CreateSeed {
    
    private final JdbcTemplate jdbcTemplate;

    public CreateSeed(DataSource datasSource) {
        jdbcTemplate = new JdbcTemplate(datasSource);
    }

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5434/cert_course");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");

        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run(args);
    }

    public void run(String... args) {
        executeSqlFile("src/main/resources/db/create.sql");
    }

    private void executeSqlFile(String filePath) {
        try {
            String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));
            jdbcTemplate.execute(sqlScript);
            System.out.println("Seed executado com sucesso");
        } catch (IOException e) {
            System.err.println("Erro ao executar arquivo SQL: " + e.getMessage());
        }

    }
}