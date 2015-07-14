package net.scales.service;

import java.util.Date;
import java.util.List;

import net.scales.model.CustomItem;
import net.scales.model.CustomUser;
import net.scales.model.Labor;

public interface LaborService {

	public List<Labor> getLaborsByPeriod(Date date, CustomUser user);

	public List<CustomItem> getTipulList(int i, String query);
}
