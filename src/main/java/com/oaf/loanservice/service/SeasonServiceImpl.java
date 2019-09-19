package com.oaf.loanservice.service;

import com.oaf.loanservice.dao.SeasonRepository;
import com.oaf.loanservice.domain.Season;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {

    private static final Logger logger = LoggerFactory.getLogger(SeasonServiceImpl.class);

    private final SeasonRepository seasonRepository;

    public SeasonServiceImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public int[] save(List<Season> seasons) {

        logger.info("event=SAVE_BULK_SEASON_START,  seasonsCount={}", seasons.size());
        long start = System.currentTimeMillis();

        int[] savedSeasons = seasonRepository.save(seasons);

        logger.info("event=SAVE_BULK_SEASON_END, savedSeasons={}, timeMS={}", savedSeasons, System.currentTimeMillis() - start);
        return savedSeasons;
    }
}
