const express = require('express');
const cors = require('cors');
const app = express();
const port = 8082;

// Enable CORS
app.use(cors());
app.use(express.json());

// Set context path
app.use('/api/v1', (req, res, next) => {
  next();
});

// Root endpoint
app.get('/api/v1', (req, res) => {
  res.json({
    message: 'IT Company API',
    version: '1.0.0',
    status: 'running'
  });
});

// Health check endpoint
app.get('/api/v1/health', (req, res) => {
  res.json({
    status: 'UP',
    message: 'Mock backend is running',
    timestamp: new Date().toISOString()
  });
});

// Auth endpoints
app.post('/api/v1/auth/login', (req, res) => {
  const { email, password, role } = req.body;
  
  // Simple mock authentication
  if (email === 'parent-admin@ssrmtech.com' && password === 'admin123' && role === 'PARENT_ADMIN') {
    res.json({
      token: 'mock-jwt-token',
      id: 1,
      name: 'Parent Admin',
      email: 'parent-admin@ssrmtech.com',
      role: 'PARENT_ADMIN'
    });
  } else if (email === 'admin@ssrmtech.com' && password === 'admin123' && role === 'ADMIN') {
    res.json({
      token: 'mock-jwt-token-admin',
      id: 2,
      name: 'Regular Admin',
      email: 'admin@ssrmtech.com',
      role: 'ADMIN'
    });
  } else if (email === 'user@ssrmtech.com' && password === 'user123' && role === 'USER') {
    res.json({
      token: 'mock-jwt-token-user',
      id: 3,
      name: 'Regular User',
      email: 'user@ssrmtech.com',
      role: 'USER'
    });
  } else {
    res.status(401).json({
      message: 'Invalid credentials'
    });
  }
});

// Register endpoint
app.post('/api/v1/auth/register', (req, res) => {
  const { name, email, password, role } = req.body;
  
  // Simple mock registration
  res.status(201).json({
    message: 'User registered successfully',
    id: Math.floor(Math.random() * 1000) + 10,
    status: 'PENDING'
  });
});

// User management endpoints
app.get('/api/v1/users', (req, res) => {
  // Mock user list
  res.json([
    {
      id: 1,
      name: 'Parent Admin',
      email: 'parent-admin@ssrmtech.com',
      role: 'PARENT_ADMIN',
      status: 'ACTIVE',
      department: 'Administration',
      phone: '123-456-7890'
    },
    {
      id: 2,
      name: 'Regular Admin',
      email: 'admin@ssrmtech.com',
      role: 'ADMIN',
      status: 'ACTIVE',
      department: 'IT',
      phone: '123-456-7891'
    },
    {
      id: 3,
      name: 'Regular User',
      email: 'user@ssrmtech.com',
      role: 'USER',
      status: 'ACTIVE',
      department: 'Development',
      phone: '123-456-7892'
    },
    {
      id: 4,
      name: 'Pending Admin',
      email: 'pending-admin@ssrmtech.com',
      role: 'ADMIN',
      status: 'PENDING',
      department: 'Marketing',
      phone: '123-456-7893'
    },
    {
      id: 5,
      name: 'Pending User',
      email: 'pending-user@ssrmtech.com',
      role: 'USER',
      status: 'PENDING',
      department: 'Sales',
      phone: '123-456-7894'
    }
  ]);
});

app.get('/api/v1/users/:id', (req, res) => {
  const id = parseInt(req.params.id);
  // Mock single user
  res.json({
    id: id,
    name: `User ${id}`,
    email: `user${id}@ssrmtech.com`,
    role: id === 1 ? 'PARENT_ADMIN' : (id % 2 === 0 ? 'ADMIN' : 'USER'),
    status: 'ACTIVE',
    department: 'Development',
    phone: `123-456-${7890 + id}`
  });
});

app.post('/api/v1/users/:id/approve', (req, res) => {
  const id = parseInt(req.params.id);
  // Mock approval
  res.json({
    id: id,
    name: `User ${id}`,
    email: `user${id}@ssrmtech.com`,
    role: id % 2 === 0 ? 'ADMIN' : 'USER',
    status: 'ACTIVE',
    department: 'Development',
    phone: `123-456-${7890 + id}`
  });
});

app.post('/api/v1/users/:id/reject', (req, res) => {
  const id = parseInt(req.params.id);
  // Mock rejection
  res.json({
    id: id,
    name: `User ${id}`,
    email: `user${id}@ssrmtech.com`,
    role: id % 2 === 0 ? 'ADMIN' : 'USER',
    status: 'INACTIVE',
    department: 'Development',
    phone: `123-456-${7890 + id}`
  });
});

// Timesheet endpoints
app.get('/api/v1/timesheets', (req, res) => {
  // Mock timesheet list
  const status = req.query.status;
  let timesheets = [
    {
      id: 1,
      userId: 3,
      weekEnding: '2023-07-14',
      status: 'APPROVED',
      totalHours: 40,
      submittedDate: '2023-07-14T12:00:00Z',
      reviewedDate: '2023-07-15T10:00:00Z',
      reviewedBy: 1,
      comments: 'Good work',
      hours: { 'Monday': 8, 'Tuesday': 8, 'Wednesday': 8, 'Thursday': 8, 'Friday': 8 }
    },
    {
      id: 2,
      userId: 3,
      weekEnding: '2023-07-21',
      status: 'PENDING',
      totalHours: 42,
      submittedDate: '2023-07-21T14:00:00Z',
      hours: { 'Monday': 9, 'Tuesday': 8, 'Wednesday': 8, 'Thursday': 9, 'Friday': 8 }
    },
    {
      id: 3,
      userId: 5,
      weekEnding: '2023-07-21',
      status: 'PENDING',
      totalHours: 38,
      submittedDate: '2023-07-21T15:30:00Z',
      hours: { 'Monday': 8, 'Tuesday': 7, 'Wednesday': 8, 'Thursday': 7, 'Friday': 8 }
    },
    {
      id: 4,
      userId: 3,
      weekEnding: '2023-07-28',
      status: 'REJECTED',
      totalHours: 45,
      submittedDate: '2023-07-28T16:00:00Z',
      reviewedDate: '2023-07-29T09:00:00Z',
      reviewedBy: 2,
      comments: 'Too many hours without prior approval',
      hours: { 'Monday': 9, 'Tuesday': 9, 'Wednesday': 9, 'Thursday': 9, 'Friday': 9 }
    }
  ];
  
  if (status) {
    timesheets = timesheets.filter(t => t.status === status.toUpperCase());
  }
  
  res.json(timesheets);
});

app.get('/api/v1/timesheets/:id', (req, res) => {
  const id = parseInt(req.params.id);
  // Mock single timesheet
  res.json({
    id: id,
    userId: 3,
    weekEnding: '2023-07-21',
    status: 'PENDING',
    totalHours: 42,
    submittedDate: '2023-07-21T14:00:00Z',
    hours: { 'Monday': 9, 'Tuesday': 8, 'Wednesday': 8, 'Thursday': 9, 'Friday': 8 }
  });
});

app.get('/api/v1/timesheets/user/:userId', (req, res) => {
  const userId = parseInt(req.params.userId);
  // Mock user timesheets
  res.json([
    {
      id: 1,
      userId: userId,
      weekEnding: '2023-07-14',
      status: 'APPROVED',
      totalHours: 40,
      submittedDate: '2023-07-14T12:00:00Z',
      reviewedDate: '2023-07-15T10:00:00Z',
      reviewedBy: 1,
      comments: 'Good work',
      hours: { 'Monday': 8, 'Tuesday': 8, 'Wednesday': 8, 'Thursday': 8, 'Friday': 8 }
    },
    {
      id: 2,
      userId: userId,
      weekEnding: '2023-07-21',
      status: 'PENDING',
      totalHours: 42,
      submittedDate: '2023-07-21T14:00:00Z',
      hours: { 'Monday': 9, 'Tuesday': 8, 'Wednesday': 8, 'Thursday': 9, 'Friday': 8 }
    }
  ]);
});

app.post('/api/v1/timesheets/:id/approve', (req, res) => {
  const id = parseInt(req.params.id);
  const { comments } = req.body;
  // Mock timesheet approval
  res.json({
    id: id,
    userId: 3,
    weekEnding: '2023-07-21',
    status: 'APPROVED',
    totalHours: 42,
    submittedDate: '2023-07-21T14:00:00Z',
    reviewedDate: new Date().toISOString(),
    reviewedBy: 1,
    comments: comments || 'Approved',
    hours: { 'Monday': 9, 'Tuesday': 8, 'Wednesday': 8, 'Thursday': 9, 'Friday': 8 }
  });
});

app.post('/api/v1/timesheets/:id/reject', (req, res) => {
  const id = parseInt(req.params.id);
  const { comments } = req.body;
  // Mock timesheet rejection
  res.json({
    id: id,
    userId: 3,
    weekEnding: '2023-07-21',
    status: 'REJECTED',
    totalHours: 42,
    submittedDate: '2023-07-21T14:00:00Z',
    reviewedDate: new Date().toISOString(),
    reviewedBy: 1,
    comments: comments || 'Rejected',
    hours: { 'Monday': 9, 'Tuesday': 8, 'Wednesday': 8, 'Thursday': 9, 'Friday': 8 }
  });
});

// Email logs endpoint
app.get('/api/v1/email-logs', (req, res) => {
  // Mock email logs
  res.json([
    {
      id: 1,
      toEmail: 'parent-admin@ssrmtech.com',
      fromEmail: 'noreply@ssrmtech.com',
      subject: 'New User Registration',
      body: 'A new user has registered and requires your approval.',
      type: 'USER_APPROVAL',
      status: 'SENT',
      sentDate: '2023-07-10T09:30:00Z'
    },
    {
      id: 2,
      toEmail: 'admin@ssrmtech.com',
      fromEmail: 'noreply@ssrmtech.com',
      subject: 'New User Registration',
      body: 'A new user has registered and requires your approval.',
      type: 'USER_APPROVAL',
      status: 'SENT',
      sentDate: '2023-07-11T10:15:00Z'
    },
    {
      id: 3,
      toEmail: 'user@ssrmtech.com',
      fromEmail: 'noreply@ssrmtech.com',
      subject: 'Account Approved',
      body: 'Your account has been approved. You can now log in to the system.',
      type: 'USER_APPROVED',
      status: 'SENT',
      sentDate: '2023-07-12T14:20:00Z'
    },
    {
      id: 4,
      toEmail: 'user@ssrmtech.com',
      fromEmail: 'noreply@ssrmtech.com',
      subject: 'Timesheet Approved',
      body: 'Your timesheet for the week ending 2023-07-14 has been approved.',
      type: 'TIMESHEET_APPROVED',
      status: 'SENT',
      sentDate: '2023-07-15T11:45:00Z'
    },
    {
      id: 5,
      toEmail: 'user@ssrmtech.com',
      fromEmail: 'noreply@ssrmtech.com',
      subject: 'Timesheet Rejected',
      body: 'Your timesheet for the week ending 2023-07-28 has been rejected. Reason: Too many hours without prior approval.',
      type: 'TIMESHEET_REJECTED',
      status: 'SENT',
      sentDate: '2023-07-29T09:30:00Z'
    }
  ]);
});

// Create a new timesheet endpoint
app.post('/api/v1/timesheets', (req, res) => {
  const timesheet = req.body;
  // Mock timesheet creation
  res.status(201).json({
    id: Math.floor(Math.random() * 1000) + 10,
    userId: timesheet.userId,
    weekEnding: timesheet.weekEnding,
    status: 'PENDING',
    totalHours: timesheet.totalHours || Object.values(timesheet.hours).reduce((sum, h) => sum + h, 0),
    submittedDate: new Date().toISOString(),
    hours: timesheet.hours
  });
});

// Update a timesheet endpoint
app.put('/api/v1/timesheets/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const timesheet = req.body;
  // Mock timesheet update
  res.json({
    id: id,
    userId: timesheet.userId,
    weekEnding: timesheet.weekEnding,
    status: 'PENDING',
    totalHours: timesheet.totalHours || Object.values(timesheet.hours).reduce((sum, h) => sum + h, 0),
    submittedDate: timesheet.submittedDate || new Date().toISOString(),
    hours: timesheet.hours
  });
});

// Start the server
app.listen(port, () => {
  console.log(`Mock backend running at http://localhost:${port}/api/v1`);
});