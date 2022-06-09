package com.mhk.studyspringbatch.job;

import com.mhk.studyspringbatch.entity.Pay;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JdbcCursorItemReaderJobConfiguration {

	private static final int chunkSize = 10;

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final DataSource dataSource;

	@Bean
	public Job jdbcCursorItemReaderJob() {
		return jobBuilderFactory.get("jdbcCursorItemReaderJob")
			.start(jdbcCursorItemReaderStep())
			.build();
	}

	@Bean
	public Step jdbcCursorItemReaderStep() {
		return stepBuilderFactory.get("jdbcCursorItemReaderStep")
			.<Pay, Pay>chunk(chunkSize) // 첫 번째 Pay는 Reader에서 반환할 타입, 두 번째 Pay는 Writer에 파라미터로 넘어올 타입
			.reader(jdbcCursorItemReader())
			.writer(jdbcCursorItemWriter())
			.build();
	}

	@Bean
	public JdbcCursorItemReader<Pay> jdbcCursorItemReader() {
		return new JdbcCursorItemReaderBuilder<Pay>()
			.fetchSize(chunkSize)
			.dataSource(dataSource)
			.rowMapper(new BeanPropertyRowMapper<>(Pay.class))
			.sql("SELECT id, amount, tx_name, tx_date_time FROM pay")
			.name("jdbcCursorItemReader")
			.build();
	}

	private ItemWriter<Pay> jdbcCursorItemWriter() {
		return list -> {
			for (Pay pay : list) {
				log.info("Current Pay = {}", pay);
			}
		};
	}
}
