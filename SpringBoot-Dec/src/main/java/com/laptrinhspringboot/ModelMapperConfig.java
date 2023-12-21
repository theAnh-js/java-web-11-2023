package com.laptrinhspringboot;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	// Vì trong spring chưa có sẵn bean ModelMapper
	// Nên ta phải tự cấu hình để báo cho spring
	// biết thằng này mình cũng tạo bean cho nó.
	@Bean
	public ModelMapper getModelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		// MatchingStrategies.STANDARD được sử dụng, nó ánh xạ các trường giống nhau dựa trên tên và kiểu dữ liệu.
		
		return modelMapper;
	}

}
