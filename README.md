# LightBind

**Take control of entity brightness with simple configuration.**

LightBind provides an easy way to set fixed light levels for specific entities in Minecraft 1.16.5 with Embeddium. Instead of relying on the game's dynamic lighting calculations, you can assign consistent brightness values to any entity.

## What's this for?

Some entities - particularly those with special visual effects or modified sizes - may not interact well with Sodium's lighting system. The Flame Strike entity from Cataclysm mod is a perfect example: when its radius is increased, the lighting calculations become unnecessarily complex and expensive.

With LightBind, you can ensure these entities always appear at your desired brightness level, providing consistent visuals while offering potential performance benefits as a nice bonus.

## How to use

Simply edit `lightbind.json` and add entries using this format:
`mod_id:entity_name;brightness_value`

**Default example:**
`cataclysm:flame_strike;240`

This configuration makes Flame Strike entities always render at maximum brightness (240), ensuring they remain clearly visible regardless of their environment or size modifications.

## Perfect for:
- Ensuring special effects entities maintain consistent visibility
- Fixing lighting issues with resized or custom entities
- Simplifying lighting for entities that should always glow
- Customizing visual appearance without complex resource packs

LightBind puts you in control of entity lighting - simple, configurable, and effective.