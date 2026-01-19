# Changelog

## 1.3.4 - 1.21.5 - 2026-01-19
- Port to 1.21.5.

## 1.3.4 - 2026-01-19
- Fixed chimneys not stacking properly.

## 1.3.3 - 1.21.4 - 2025-12-14
- Port to 1.21.4

## 1.3.3 - 2025-03-14
- [NeoForge] Chimneys on Create contraptions are now interactable (for smoke blocking/restoring)
- [NeoForge] Fixed Chimneys not emitting signal smoke when stacked on a Create contraption.

## 1.3.2 - 2025-03-02
- Added Create compatibility. 

## 1.3.1 - 2024-10-16
- Fixed axe not being damaged when soot is scraped.

## 1.3.0 - 2024-08-09
- Now has an official Fabric support.
- Added advancement and stat for scraping soot.
- Wind effect on a smoke is now a bit smoother.
- Wind now also affects smoke from Campfires.
- Soot Scraping is now only configured through recipes. Loot Tables implementation has been removed. 

## 1.2.1 - 2024-01-26
- Fixed stacked chimneys not emitting signal smoke 

## 1.2.0 - 2023-12-27
- Added Cobblestone Chimney
- Added Mud Brick Chimney
- Added Iron Chimney
<br><br>
- `sootychimneys:smoke_blocking` block tag can now be used to control what blocks, besides other chimneys, will block the smoke if placed on top of a chimney.
- `sootychimneys:smoke_boosting` block tag can now be used to control what blocks, besides other chimneys, will make a chimney emit a signal-smoke when below it.
<br><br>
- **Soot Scraping** is now handled through a recipe.
  - Loot Tables way is still available and accessed through config.
- When chimneys are stacked, lower chimney model will be adjusted to connect them better. 

## 1.1.1 - 2023-05-23
- Now supports Create 0.5.1a+

## 1.1.0 - 2023-04-18
- Chimney that has another chimney underneath will now emit a Signal Smoke (like a hay bale under a campfire)
- Chimney that has another chimney on top of it will not be emitting smoke.
- Slightly reduced wind effect on smoke. This setting is now configurable in config.
- Changed tags to be inline with vanilla naming. _forge:chimney_ -> _forge:chimneys_.
- Fixed Dirty Stone Brick Chimney particles.

## 1.0.0 - 2022-12-25
- Added smoke blocking mechanic: right click chimney with an empty hand to enable/disable smoke.
- Added JEI/REI compatibility.

## 0.6.0 - 2022-08-07
- Added compatibility with Create. Chimneys will now emit smoke when on a contraption.

## 0.5.1 - 2022-06-27
- Changed recipes to use minecraft:coals tag. Thanks *cosmicgelatin* for a correction.
- Fixed client crash when teleporting away from chimneys.

## 0.5.0 - 2022-06-23
- When scraping soot from the chimney - there is now a chance (0.5 - 0.75, depending on chimney) that Black Dye will drop.
  - Dropped items can be configured by a data pack. Each chimney can have its own drops.

- Fixed Copper Chimney recipe.
- Fixed Stone Brick Chimney bounding box.

## 0.4.0 - 2022-06-21
- Added Copper Chimney. - I'm not 100% happy with the model and textures of this one. If you have ideas or suggestions - feel free to write a comment or create an issue on github.
- Changed chimney placing/breaking sounds.
- Changed Stone Brick Chimney model slightly.
- Small touchups to other models/textures.

## 0.3.0 - 2022-06-20
- Added Terracotta Chimney.
- Due to the internal changes - existing chimneys may need to be replaced.

## 0.2.0 - 2022-06-18
- Added Stone Brick Chimney.

## 0.1.1 - 2022-06-17
- Added Dirty Brick Chimney block and item (dirty chimney is a different block now).