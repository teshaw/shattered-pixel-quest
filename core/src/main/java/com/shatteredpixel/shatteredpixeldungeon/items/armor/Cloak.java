/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2025 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Cloak extends Armor {

	{
		image = ItemSpriteSheet.CLOAK_HOLDER;
		bones = true;
	}
	
	public Cloak() {
		super( 1 );
	}

	@Override
	public String name() {
		return "cloak";
	}

	@Override
	public String info() {
		return "A protective cloak that provides defense and concealment.";
	}
	
	@Override
	public boolean doEquip( Hero hero ) {
		// Handle the equipping logic for cloak slot
		detach(hero.belongings.backpack);

		Armor oldCloak = hero.belongings.cloak;
		if (hero.belongings.cloak == null || hero.belongings.cloak.doUnequip( hero, true, false )) {
			
			hero.belongings.cloak = this;
			
			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
			}
			
			activate( hero );
			Talent.onItemEquipped(hero, this);
			Statistics.itemsEquipped++;
			return true;
			
		} else {
			
			collect( hero.belongings.backpack );
			return false;
		}
	}

	@Override
	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {
		if (super.doUnequip( hero, collect, single )) {
			
			hero.belongings.cloak = null;
			return true;
			
		} else {
			
			return false;
		}
	}

	@Override
	public boolean isEquipped( Hero hero ) {
		return hero != null && hero.belongings.cloak() == this;
	}
}