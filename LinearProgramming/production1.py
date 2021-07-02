

#!pip install pulp

from pulp import *

Ingredients = ['zorat', 'sang_ahak', 'soya', 'mahi']

# A dictionary of the costs of each of the Ingredients is created
costs = {'zorat': 0.12, 
         'sang_ahak': 0.20, 
         'soya': 0.24, 
         'mahi': 0.12}
upperbound = {'zorat':6000, 
              'sang_ahak': 10000, 
              'soya':4000, 
              'mahi': 5000}
              
# A dictionary of the protein percent in each of the Ingredients is created
vitPercent  = {'zorat': 8, 
                'sang_ahak': 6, 
                'soya': 10, 
                'mahi': 4}

# A dictionary of the fat percent in each of the Ingredients is created
proteinPercent = {'zorat': 10, 
                  'sang_ahak':5, 
                  'soya': 12, 
                  'mahi': 8}

# A dictionary of the fibre percent in each of the Ingredients is created
calPercent = {'zorat': 6, 
              'sang_ahak': 10, 
              'soya': 6, 
              'mahi': 6}

# A dictionary of the salt percent in each of the Ingredients is created
charbiPercent = {'zorat': 8, 
                  'sang_ahak':6, 
                  'soya': 6, 
                  'mahi': 9}

prob = LpProblem("The food Problem", LpMinimize)

# Create the 'prob' variable to contain the problem data
prob = LpProblem("The food Problem", LpMinimize)

# A dictionary called 'ingredient_vars' is created to contain the referenced Variables
# ingredient_vars = LpVariable.dicts("Ingr",Ingredients,lowBound=0 ,upBound =upperbound )
ingredient_vars = {}
keys = range(4)
c=0 
for i in Ingredients:
        ingredient_vars[i] = LpVariable( i ,lowBound=0 ,upBound =upperbound[i] )
        c =c+1
print(ingredient_vars)

prob += lpSum([costs[i]*ingredient_vars[i] for i in Ingredients]), "Total Cost of Ingredients per can"

# The five constraints are added to 'prob'
prob += lpSum([ingredient_vars[i] for i in Ingredients]) == 24000, "PercentagesSum"
prob += lpSum([vitPercent[i] * ingredient_vars[i] for i in Ingredients]) >= 128000, "vitRequirement "
prob += lpSum([proteinPercent[i] * ingredient_vars[i] for i in Ingredients]) >= 144000, "ProteinRequirement"
prob += lpSum([calPercent[i] * ingredient_vars[i] for i in Ingredients]) >= 154000, "calRequirement"
prob += lpSum([charbiPercent[i] * ingredient_vars[i] for i in Ingredients]) >= 96000, "charbiRequirement_a"
# prob += lpSum([charbiPercent[i] * ingredient_vars[i] for i in Ingredients]) <= 164000, "charbiRequirement_b"

# Hereafter the code is the same as before, abbreviated here

prob.solve()

# 6. Print solution status
print("Status:",LpStatus[prob.status])

# 7. Print out the optimal value of each variable
for v in prob.variables():
    print(v.name, "=", v.varValue)


# Print the objective function value of the optimal solution
print("Total Cost of Ingredient per can = ", value(prob.objective))