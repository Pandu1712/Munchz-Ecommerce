CREATE EXTENSION IF NOT EXISTS "pgcrypto";


--users table
create table auth_users(
  id uuid primary key default gen_random_uuid(),
  email varchar(320) unique not null,
  phone varchar(32),
  is_email_verified boolean default false,
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);

--roles
create table roles(
     id SERIAL PRIMARY KEY,
     name varchar(50) unique not null
);

--user roles
create table user_roles(
  user_id uuid not null references auth_users(id) on delete cascade,
  role_id int not null references roles(id) on delete cascade,
  assigned_by uuid null,
  assigned_at TIMESTAMP default now(),
  primary key (user_id,role_id)
);


--permissions
create table permissions (
  id serial primary key,
  name varchar(150) unique not null,
  description TEXT
);

--role permission mapping
create table role_permissions (
  role_id int not null references roles(id) on delete cascade,
  permission_id int not null references permissions(id) on delete cascade,
  primary key(role_id,permission_id)
);


--otps(for email & registrations)
create table user_otps (
    id uuid primary key default gen_random_uuid(),
    user_id uuid references auth_users(id) on delete cascade,
    otp_hash varchar(100) not null,
    purpose varchar(50) not null,
    expires_at TIMESTAMP not null,
    consumed boolean default false,
    created_at TIMESTAMP DEFAULT now()
);


--audit logs
CREATE TABLE audit_logs (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  actor_user_id UUID,
  actor_service VARCHAR(100),
  action VARCHAR(200),
  target_table VARCHAR(100),
  target_id UUID,
  payload JSONB,
  created_at TIMESTAMP DEFAULT now()
);