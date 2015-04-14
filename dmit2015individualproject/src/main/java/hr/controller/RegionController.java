package hr.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.JSFUtil;
import hr.domain.Region;
import hr.service.RegionService;

@Named("regionBean")
@ViewScoped
public class RegionController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private RegionService regionService;
	
	private List<Region> regions;

	public RegionController() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			regions = regionService.findAll();
		} catch( Exception e) {
			JSFUtil.addErrorMessage("Error retreiving regions: " + e.getMessage());
		}
	}

	public List<Region> getRegions() {
		return regions;
	}

	public SelectItem[] getRegionOptions() {
		int optionCount = regions.size();
		SelectItem[] options = new SelectItem[ optionCount + 1 ];
		int index = 0;
		options[index++] = new SelectItem("", "Select");
		for(Region region: regions ) {
			options[index++] = new SelectItem( region.getId(), region.getName() );
		}
		return options;
	}
}
