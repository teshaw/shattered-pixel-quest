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

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Cloak;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Gauntlets;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Helm;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.InventorySlot;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;

public class WndEquipment extends Window {
	
	private static final int WIDTH = 160;
	private static final int HEIGHT = 120;
	
	private static final int HERO_SIZE = 48;
	private static final int SLOT_SIZE = 24;
	private static final int SLOT_MARGIN = 2;
	
	public WndEquipment() {
		super();
		
		resize(WIDTH, HEIGHT);
		
		Hero hero = Dungeon.hero;
		Belongings belongings = hero.belongings;
		
		// Title
		RenderedTextBlock title = PixelScene.renderTextBlock(Messages.get(this, "title"), 12);
		title.hardlight(TITLE_COLOR);
		title.setPos((WIDTH - title.width()) / 2, 2);
		add(title);
		
		// Hero sprite in center
		Image heroSprite = HeroSprite.avatar(hero);
		heroSprite.scale.set(2.0f);
		heroSprite.x = (WIDTH - HERO_SIZE) / 2;
		heroSprite.y = title.bottom() + 8;
		add(heroSprite);
		
		// Equipment slots around the hero
		float heroLeft = heroSprite.x;
		float heroRight = heroSprite.x + HERO_SIZE;
		float heroTop = heroSprite.y;
		float heroBottom = heroSprite.y + HERO_SIZE;
		float heroMidY = heroTop + HERO_SIZE / 2;
		float heroMidX = heroLeft + HERO_SIZE / 2;
		
		// Helm slot (top)
		InventorySlot helmSlot = new InventorySlot(belongings.helm() != null ? belongings.helm() : new WndBag.Placeholder(ItemSpriteSheet.HELM_HOLDER)){
			@Override
			protected void onClick() {
				Game.scene().addToFront(new WndUseItem(WndEquipment.this, belongings.helm()));
			}
		};
		helmSlot.setRect(heroMidX - SLOT_SIZE/2, heroTop - SLOT_SIZE - SLOT_MARGIN, SLOT_SIZE, SLOT_SIZE);
		add(helmSlot);
		
		// Weapon slot (left)
		InventorySlot weaponSlot = new InventorySlot(belongings.weapon() != null ? belongings.weapon() : new WndBag.Placeholder(ItemSpriteSheet.WEAPON_HOLDER)){
			@Override
			protected void onClick() {
				Game.scene().addToFront(new WndUseItem(WndEquipment.this, belongings.weapon()));
			}
		};
		weaponSlot.setRect(heroLeft - SLOT_SIZE - SLOT_MARGIN, heroMidY - SLOT_SIZE/2, SLOT_SIZE, SLOT_SIZE);
		add(weaponSlot);
		
		// Gauntlets slot (right)
		InventorySlot gauntletsSlot = new InventorySlot(belongings.gauntlets() != null ? belongings.gauntlets() : new WndBag.Placeholder(ItemSpriteSheet.GAUNTLET_HOLDER)){
			@Override
			protected void onClick() {
				Game.scene().addToFront(new WndUseItem(WndEquipment.this, belongings.gauntlets()));
			}
		};
		gauntletsSlot.setRect(heroRight + SLOT_MARGIN, heroMidY - SLOT_SIZE/2, SLOT_SIZE, SLOT_SIZE);
		add(gauntletsSlot);
		
		// Armor slot (center-left of hero)
		InventorySlot armorSlot = new InventorySlot(belongings.armor() != null ? belongings.armor() : new WndBag.Placeholder(ItemSpriteSheet.ARMOR_HOLDER)){
			@Override
			protected void onClick() {
				Game.scene().addToFront(new WndUseItem(WndEquipment.this, belongings.armor()));
			}
		};
		armorSlot.setRect(heroLeft - SLOT_SIZE - SLOT_MARGIN, heroTop + 6, SLOT_SIZE, SLOT_SIZE);
		add(armorSlot);
		
		// Cloak slot (bottom)
		InventorySlot cloakSlot = new InventorySlot(belongings.cloak() != null ? belongings.cloak() : new WndBag.Placeholder(ItemSpriteSheet.CLOAK_HOLDER)){
			@Override
			protected void onClick() {
				Game.scene().addToFront(new WndUseItem(WndEquipment.this, belongings.cloak()));
			}
		};
		cloakSlot.setRect(heroMidX - SLOT_SIZE/2, heroBottom + SLOT_MARGIN, SLOT_SIZE, SLOT_SIZE);
		add(cloakSlot);
		
		// Ring and artifact slots (bottom corners)
		InventorySlot ringSlot = new InventorySlot(belongings.ring() != null ? belongings.ring() : new WndBag.Placeholder(ItemSpriteSheet.RING_HOLDER)){
			@Override
			protected void onClick() {
				Game.scene().addToFront(new WndUseItem(WndEquipment.this, belongings.ring()));
			}
		};
		ringSlot.setRect(heroLeft - SLOT_SIZE - SLOT_MARGIN, heroBottom - 6, SLOT_SIZE, SLOT_SIZE);
		add(ringSlot);
		
		InventorySlot artifactSlot = new InventorySlot(belongings.artifact() != null ? belongings.artifact() : new WndBag.Placeholder(ItemSpriteSheet.ARTIFACT_HOLDER)){
			@Override
			protected void onClick() {
				Game.scene().addToFront(new WndUseItem(WndEquipment.this, belongings.artifact()));
			}
		};
		artifactSlot.setRect(heroRight + SLOT_MARGIN, heroBottom - 6, SLOT_SIZE, SLOT_SIZE);
		add(artifactSlot);
	}
}