import os
from PIL import Image

# Source logo image
src = r"C:\Users\Asmitkumarsharma\Downloads\ai.png"

# Output res directory
res_dir = r"C:\Users\Asmitkumarsharma\Downloads\a1\app\src\main\res"

# Standard Android launcher icon sizes per density
mipmap_sizes = {
    "mdpi":     48,
    "hdpi":     72,
    "xhdpi":    96,
    "xxhdpi":   144,
    "xxxhdpi":  192,
}

def generate_icons(src_path, res_dir, sizes):
    img = Image.open(src_path).convert("RGBA")

    # Crop to a square (centered crop)
    w, h = img.size
    side = min(w, h)
    left   = (w - side) // 2
    top    = (h - side) // 2
    right  = left + side
    bottom = top + side
    img = img.crop((left, top, right, bottom))

    for density, px in sizes.items():
        folder = os.path.join(res_dir, f"mipmap-{density}")
        os.makedirs(folder, exist_ok=True)

        resized = img.resize((px, px), Image.Resampling.LANCZOS)

        for name in ("ic_launcher_logo.png", "ic_launcher_logo_round.png"):
            out = os.path.join(folder, name)
            resized.save(out, "PNG")

        print(f"[OK] mipmap-{density}  ({px}x{px}px)")

    print("\nDone! All icons generated.")

generate_icons(src, res_dir, mipmap_sizes)
