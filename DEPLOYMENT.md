# ShelfWise — Free Cloud Production Deployment Guide

Follow this guide to deploy your **MySQL Database**, **Spring Boot Backend**, and **React Frontend** to 100% free cloud hosting services with 24/7 availability and zero disconnect errors.

---

## Architecture

| Layer | Service | Hosting Platform | Cost |
| :--- | :--- | :--- | :--- |
| **Database** | MySQL 8.x | [Aiven](https://aiven.io/) / [TiDB Cloud](https://tidbcloud.com/) / [Railway](https://railway.app/) | Free |
| **Backend** | Spring Boot 3 + Java 17 | [Render](https://render.com/) (Web Service) | Free |
| **Frontend** | React 18 + Vite | [Vercel](https://vercel.com/) | Free |

---

## Step 1: Initialize Git and Push to GitHub

1. Open your terminal in this directory:
   ```bash
   git init
   git add .
   git commit -m "feat: configure cloud production deployment"
   ```
2. Create a new repository on [GitHub](https://github.com/new) (e.g. `ShelfWise`).
3. Push your code:
   ```bash
   git branch -M main
   git remote add origin https://github.com/<your-username>/ShelfWise.git
   git push -u origin main
   ```

---

## Step 2: Set Up Free Cloud MySQL Database

You need a cloud-hosted MySQL database that is publicly accessible to Render.

### Option A: TiDB Cloud (Recommended - Forever Free MySQL-compatible)
1. Sign up at [tidbcloud.com](https://tidbcloud.com/).
2. Create a free **Serverless Cluster**.
3. Under **Security > IP Access List**, allow access from all IPs (`0.0.0.0/0`).
4. Click **Connect** and select **General / Java**:
   - Note the **Host**, **Port** (usually `4000`), **Database** (`test` or create `library_db`), **User**, and **Password**.
   - JDBC URL format:
     ```
     jdbc:mysql://<host>:<port>/library_db?useSSL=true&verifyServerCertificate=true
     ```

### Option B: Aiven for MySQL (or Railway MySQL)
1. Create a free account on [aiven.io](https://aiven.io/) or [railway.app](https://railway.app/).
2. Create a MySQL service.
3. Copy the **Service URI**, username, password, host, and port.

---

## Step 3: Deploy Backend on Render

1. Sign up / log in to [render.com](https://render.com/).
2. Click **New +** > **Web Service**.
3. Connect your GitHub repository (`ShelfWise`).
4. Configure service details:
   - **Name**: `shelfwise-backend`
   - **Region**: Choose closest to you (e.g. Singapore, Frankfurt, Oregon)
   - **Branch**: `main`
   - **Root Directory**: `backend`
   - **Runtime**: **Docker** (Render will use `backend/Dockerfile` automatically)
   - **Instance Type**: **Free**
5. Under **Environment Variables**, add:
   - `PORT`: `8080`
   - `DB_URL`: `jdbc:mysql://<cloud-db-host>:<port>/<db_name>?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
   - `DB_USERNAME`: `<your-db-username>`
   - `DB_PASSWORD`: `<your-db-password>`
   - `JWT_SECRET`: `<any-random-string-at-least-32-chars>`
   - `FRONTEND_ORIGIN`: `http://localhost:5173,https://*.vercel.app`
6. Click **Deploy Web Service**.
7. Once deployed, Render will provide your public backend URL, e.g.:
   ```
   https://shelfwise-backend.onrender.com
   ```
8. Verify it by visiting:
   ```
   https://shelfwise-backend.onrender.com/actuator/health
   ```
   *(It should return `{"status":"UP"}`)*.

---

## Step 4: Deploy Frontend on Vercel

1. Sign up / log in to [vercel.com](https://vercel.com/).
2. Click **Add New...** > **Project**.
3. Import your GitHub repository (`ShelfWise`).
4. In the Project Configuration:
   - **Framework Preset**: `Vite`
   - **Root Directory**: Click edit and select `frontend`
   - **Build Command**: `npm run build`
   - **Output Directory**: `dist`
5. Expand **Environment Variables** and add:
   - **Name**: `VITE_API_URL`
   - **Value**: `https://shelfwise-backend.onrender.com/api` *(replace with your real Render backend URL + `/api`)*
6. Click **Deploy**.
7. In ~60 seconds, your site is live at `https://<your-project>.vercel.app`!

---

## Why "Backend Not Connected" Won't Happen

1. **Flexible CORS**: The backend now uses dynamic wildcard origin pattern matching (`https://*.vercel.app` and custom origins), so requests from Vercel will never be blocked by the browser.
2. **Cold Start Tolerance**: Free cloud instances (Render) sleep when idle. The frontend timeout has been updated to 60 seconds with an informative notification so requests wait for the server to wake up instead of failing.
3. **SPA Routing**: `frontend/vercel.json` ensures page refreshes (like on `/books` or `/login`) won't cause 404 errors.
