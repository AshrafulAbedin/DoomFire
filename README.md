# 🔥 Doom Fire Effect

A Java implementation of the classic PSX Doom fire effect using Swing. This project recreates the mesmerizing fire animation that was used in the original Doom game's PSX version.

![Doom Fire Demo](https://img.shields.io/badge/Java-Swing-orange) ![License](https://img.shields.io/badge/license-MIT-blue)

## 🎮 About

The Doom fire effect is a legendary piece of demo scene programming that creates realistic-looking fire using a simple but clever algorithm. This implementation brings that classic effect to Java with smooth animation and authentic color palette.

## ✨ Features

- **Authentic Fire Algorithm**: Implements the original PSX Doom fire propagation logic
- **Classic Color Palette**: Uses the exact 36-color gradient from the original effect
- **Smooth Animation**: Runs at 20 FPS with optimized rendering
- **Realistic Fire Behavior**: Includes flickering, decay, and horizontal drift
- **Lightweight**: Pure Java Swing implementation with no external dependencies

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code, etc.) or command line

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/doom-fire.git
cd doom-fire
```

2. Compile the Java file:
```bash
javac DoomFire.java
```

3. Run the application:
```bash
java DoomFire
```

### Usage

Simply run the program and watch the fire effect in action! The fire will automatically start animating with:
- Heat source at the bottom (maximum intensity)
- Fire propagating upward with natural decay
- Random flickering and horizontal movement
- Smooth color transitions from black → red → orange → yellow → white

## 🔧 How It Works

### Algorithm Overview

1. **Initialize**: Create a 2D grid where the bottom row represents the heat source (intensity = 35)
2. **Propagation**: For each frame, iterate from bottom to top:
   - Take the intensity value from the pixel below
   - Apply random decay (0-2 intensity points)
   - Add horizontal drift for realistic movement
   - Update the current pixel with the new intensity
3. **Rendering**: Map intensity values (0-35) to the classic fire color palette
4. **Animation**: Repeat the process ~20 times per second

### Key Components

- **ColorPalette**: Contains 36 predefined colors from black to white
- **Fire Grid**: 64x64 intensity matrix representing fire pixels
- **Update Loop**: Handles fire propagation physics
- **Render Loop**: Converts intensity values to visual output

## 🎨 Customization

You can easily customize the fire effect by modifying:

```java
// Fire grid dimensions
int fireWidth = 64, fireHeight = 64;

// Pixel size (affects visual resolution)
int pixelSize = 8;

// Animation speed
Thread.sleep(50); // Lower = faster animation

// Decay randomness
int decay = random.nextInt(3); // Increase for more aggressive decay
```

## 📁 Project Structure

```
doom-fire/
├── DoomFire.java          # Main application class
├── ColorPalette.java      # Fire color definitions (embedded)
├── README.md             # This file
└── screenshots/          # Demo images and GIFs
```

## 🖼️ Screenshots

### 🔥 Fire Effect in Action
![Doom Fire Effect](screenshots/doom-fire-demo.png)
*The mesmerizing fire effect with authentic PSX Doom colors and realistic flame movement*

### 💻 Development Environment
![Code Structure](screenshots/code-structure.png)
*Clean Java implementation with organized project structure in IntelliJ IDEA*

## 🎯 Technical Details

- **Language**: Java 8+
- **GUI Framework**: Swing
- **Rendering**: BufferedImage with Graphics2D
- **Threading**: Custom game loop with controlled frame rate
- **Performance**: Optimized for smooth real-time animation

## 🤝 Contributing

Contributions are welcome! Here are some ideas for improvements:

- [ ] Add mouse interaction to control fire intensity
- [ ] Implement different fire presets (blue fire, green fire, etc.)
- [ ] Add sound effects
- [ ] Create fullscreen mode
- [ ] Add fire particles or sparks
- [ ] Implement wind effects

### How to Contribute

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


## 🙏 Acknowledgments

- **id Software** - For creating the original Doom game
- **Fabien Sanglard** - For documenting the PSX fire effect algorithm
- **Demo Scene Community** - For inspiring creative programming techniques



---

**Enjoy the flames!** 🔥

*If you find this project interesting, please give it a ⭐ star on GitHub!*
