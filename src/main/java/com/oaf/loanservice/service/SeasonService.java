package com.oaf.loanservice.service;

import com.oaf.loanservice.domain.Season;

import java.util.List;

public interface SeasonService {

    public int[] save(List<Season> seasons);
}
