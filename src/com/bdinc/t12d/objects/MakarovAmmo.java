package com.bdinc.t12d.objects;

import java.awt.Image;

import com.bdinc.t12d.settings.ResourcesManager;

public class MakarovAmmo extends Item {
	
	public MakarovAmmo(Image sprite) {
		super(sprite);
		this.description = ResourcesManager.makarovAmmoDescStr;
		this.name = ResourcesManager.makarovAmmoName;
	}
	
	
}
