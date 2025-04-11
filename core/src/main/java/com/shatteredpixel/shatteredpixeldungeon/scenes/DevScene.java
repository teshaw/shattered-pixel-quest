package com.shatteredpixel.shatteredpixeldungeon.scenes;

import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.Toast;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Scene;

public class DevScene extends PixelScene {

	@Override
	public void create() {
		super.create();

		add(new WndOptions("Dev Tools", "Choose a command:",
			"Spawn Rat", "Back") {
			@Override
			protected void onSelect(int index) {
				if (index == 0) {
					Rat rat = new Rat();
					rat.pos = Dungeon.hero.pos + 1;
					GameScene.add(rat);
					Actor.addDelayed(rat, -1f);
					Toast toast = new Toast("Spawned a rat!");
					add(toast);
					ShatteredPixelDungeon.switchScene(GameScene.class);
				} else {
					ShatteredPixelDungeon.switchScene(TitleScene.class);
				}
			}
		});
	}
}
