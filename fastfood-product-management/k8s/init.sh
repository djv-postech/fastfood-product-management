#!/bin/bash

#cd k8s

echo "Iniciando aplicação fastfood-product-management..."

# Aplica secrets
kubectl apply -f fastfood-product-management-sql-secrets.yml &&
kubectl apply -f fastfood-product-management-secrets.yml &&

# Aplica serviços
kubectl apply -f fastfood-product-management-sql-svc.yml &&
kubectl apply -f fastfood-product-management-svc.yml &&

# Aplica StatefulSet
kubectl apply -f fastfood-product-management-sql-statefulset.yml &&

# Aplica Deployment
kubectl apply -f fastfood-product-management-deployment.yml &&

# Aplica HorizontalPodAutoscaler (HPA)
#kubectl apply -f fastfood-hpa.yml &&

# Aplica componentes
#kubectl apply -f components.yaml

echo "Aplicação inicializada!"