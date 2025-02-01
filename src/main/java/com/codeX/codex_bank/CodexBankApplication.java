package com.codeX.codex_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info (
		title = " The Codex Bank App",
		description = "Backend Rest APIs for codeX-Bank ",
		version = "v1.0",
		contact = @Contact(
			name = "Abhishek Kumar",
			email = "Abhishek.k51508@gmail.com",
			url = "https://github.com/Abhi15code/codeX-bank"  
			),
			license = @License(
				name = "The Codex",
				url = "https://github.com/Abhi15code/codeX-bank"

			)
		),
		externalDocs = @ExternalDocumentation(
			description = "This is codex bank documentation",
			url = "https://github.com/Abhi15code/codeX-bank"
		)
)

public class CodexBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodexBankApplication.class, args);
	}

}
