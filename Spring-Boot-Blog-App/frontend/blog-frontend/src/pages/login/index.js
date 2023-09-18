import React, { useState } from 'react'
import { Base } from '../../component/base'
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row } from 'reactstrap'
import { LoginService } from '../../service/loginLogoutService'
import { doLoggin } from '../../service/sessionService'
import { useNavigate } from 'react-router-dom' 


export const Login = () => {

  const navigate = useNavigate()

  const [crendentials, setCredentials] = useState({
    username: '',
    password: ''
  })

  const [error, setError] = useState({
    errors: {},
    isError: false
  })

  const handleCredentials = (e, field) => {
    setCredentials({
      ...crendentials, [field]: e.target.value
    })
  }

  const handleSubmit = (e) => {
    e.preventDefault();

    LoginService(crendentials).then(data => {
      console.log('login data ->', data)

      doLoggin(data)
      navigate('/')
    }).catch(error => {
      console.log('login error ->', error)
      setError({
        errors: error,
        isError: true
      })
    })
  }

  return (
    <Base>
      <Container>
        <Row>
          <Col sm={{size: 6, offset: 3}}>
            <Card>
              <CardHeader>
                Login
              </CardHeader>
              <CardBody>
                <Form onSubmit={handleSubmit}>
                  <FormGroup>
                    <Label htmlFor='username'>Username Or Email</Label>
                    <Input 
                      type='text'
                      placeholder='username or email'
                      id='username'
                      value={crendentials.username}
                      onChange={e => handleCredentials(e, 'username')}
                    />
                  </FormGroup>

                  <FormGroup>
                    <Label htmlFor='password'>Password</Label>
                    <Input 
                      type='text'
                      placeholder='password'
                      id='password'
                      value={crendentials.password}
                      onChange={e => handleCredentials(e, 'password')}
                    />
                  </FormGroup>
                  <Button>Login</Button>
                </Form>
                
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  )
}
