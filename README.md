# A1 Gym Manager

**A1 Gym Manager** is a simple Android app for gym owners to manage members, track payments, monitor membership expiry, and generate invoices.

---

## Features

- **Add and manage gym members:** Easily register new members and keep track of their details.
- **Membership plans:** Support for 1 month, 3 months, 6 months, and 12 months membership plans.
- **Payment tracking:** Record and monitor member payments.
- **Membership expiry alerts:** Get notified when a member's plan is about to expire.
- **Member profile management:** View and update individual member profiles.
- **Premium Invoice Generation:** Automatically generate, scale, and export high-quality, pixel-perfect A4 PDF receipts. Features an advanced XML-to-PDF rendering engine, custom gym branding (Logo, Stamp), and structured billing tables.
- **Offline database support:** Fully functional without an internet connection.
- **Modern Premium UI/UX:** Clean, intuitive interface inspired by top-tier SaaS fitness applications, built with modern spacing, typography, and flat-card design philosophies.

---

## Screenshots

### Dashboard
![Dashboard](screenshot/dashboard.jpeg)

### Add Member
![Add Member 1](screenshot/add_member_1.jpeg)
![Add Member 2](screenshot/add_member_2.jpeg)

### Member List
![Member List](screenshot/member_list.jpeg)

### Member Profile
![Member Profile](screenshot/member_profile.jpeg)

### Payment History
![Payment History](screenshot/payment_history.jpeg)

### Payment Receipt (PDF)
![Payment Receipt](screenshot/payment_receipt.jpeg)

### Manage Plans
![Manage Plans](screenshot/manage_plan.jpeg)

### Settings
![Settings](screenshot/setting.jpeg)

---

## Tech Stack

- **Language:** Kotlin
- **IDE:** Android Studio
- **Database:** SQLite / Room
- **UI:** Material Design Components

---

## Project Structure

```
app/
├── activities    # Contains all the main UI screens (Activities)
├── adapters      # Adapters for rendering lists in RecyclerViews
├── database      # Room database entities, DAOs, and configuration
├── models        # Data classes representing app entities
├── ui            # Reusable UI components, fragments, and view models
└── utils         # Helper classes, constants, and extensions
```

---

## Installation Guide

Follow these steps to run the project locally on your machine.

1. **Clone the repository**
   ```bash
   git clone https://github.com/username/a1-gym-manager.git
   ```

2. **Open the project in Android Studio**
   - Launch Android Studio.
   - Click on **Open an Existing Project**.
   - Select the cloned project folder.

3. **Sync Gradle dependencies**
   - Wait for Gradle to download and configure dependencies.

4. **Run the app**
   - Connect an Android device or start an emulator.
   - Click the **Run** button to install the app.

---

## Build APK

To generate a standalone APK file for installation:

1. Open Android Studio.
2. Go to **Build** → **Generate Signed Bundle / APK** in the top menu.
3. Choose **APK** and click Next.
4. Provide the signing key details (or build a debug APK via Build → Build Bundle(s) / APK(s) → Build APK(s)).
5. Click **Finish** to build the project.

**APK location:**
```
app/build/outputs/apk/release/
```

---

## Database

The app uses an offline SQLite/Room database with the following core tables:

- **Members Table:** Stores individual member details (e.g., name, phone number).
- **Payments Table:** Stores all payment transactions and history.
- **Plans Table:** Stores predefined membership plans and their durations.

---

## Future Improvements

- [ ] QR code attendance system
- [ ] WhatsApp payment reminders
- [ ] Cloud backup
- [ ] Admin dashboard
- [ ] Multi-gym support

---

## Contributing

Contributions are always welcome! Here's how you can help:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/AmazingFeature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the branch (`git push origin feature/AmazingFeature`)
6. Submit a pull request

---

## License

This project is licensed under the MIT License.

---

## Author

- **Name:** Asmit
- **Project:** A1 Gym Manager
