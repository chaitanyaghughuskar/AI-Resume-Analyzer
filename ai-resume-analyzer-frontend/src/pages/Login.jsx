function Login() {
  return (
    <div className="container mt-5">
      <div className="row justify-content-center">

        <div className="col-md-5">

          <div className="card shadow p-4">

            <h2 className="text-center mb-4">
              Login
            </h2>

            <input
              type="email"
              placeholder="Enter Email"
              className="form-control mb-3"
            />

            <input
              type="password"
              placeholder="Enter Password"
              className="form-control mb-3"
            />

            <button className="btn btn-primary w-100">
              Login
            </button>

          </div>

        </div>

      </div>
    </div>
  );
}

export default Login;